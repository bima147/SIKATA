package coid.bcafinance.bpsringbootfinal.service;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 02/03/2024 19:40
@Last Modified 02/03/2024 19:40
Version 1.0
*/

import coid.bcafinance.bpsringbootfinal.core.Crypto;
import coid.bcafinance.bpsringbootfinal.core.IService;
import coid.bcafinance.bpsringbootfinal.core.security.JwtUtility;
import coid.bcafinance.bpsringbootfinal.handler.ResponseHandler;
import coid.bcafinance.bpsringbootfinal.core.security.BcryptImpl;
import coid.bcafinance.bpsringbootfinal.model.Access;
import coid.bcafinance.bpsringbootfinal.model.Menu;
import coid.bcafinance.bpsringbootfinal.model.User;
import coid.bcafinance.bpsringbootfinal.repo.UsersRepo;
import coid.bcafinance.bpsringbootfinal.util.ExecuteSMTP;
import coid.bcafinance.bpsringbootfinal.util.TransformationData;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UsersService implements IService<User>, UserDetailsService {
    private Map<String,Object> mapz = new HashMap<>();

    private StringBuilder sBuild = new StringBuilder();

    private String[] strExceptionArr = new String[2];

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public ResponseEntity<Object> save(User user, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> saveBatch(List<User> lt, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> edit(Long id, User user, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> find(Pageable pageable, String columFirst, String valueFirst, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> dataToExport(MultipartFile multipartFile, HttpServletRequest request) {
        return null;
    }

    /*flow untuk registrasi STEP 1*/
    public ResponseEntity<Object> checkRegis(User user, HttpServletRequest request) {//RANGE RGS 001-010
        if(user==null)
        {
            return new ResponseHandler().generateResponse(
                    "Data tidak Valid",//message
                    HttpStatus.BAD_REQUEST,//httpstatus
                    null,//object
                    "FVRGS001",//errorCode Fail Validation modul-code RGS sequence 001 range 001 - 010
                    request
            );
        }
        int intVerification = new Random().nextInt(100000,999999);//TOKEN YANG AKAN DIKIRIM KE EMAIL
        Optional<User> opUserResult = usersRepo.findTop1ByUsernameOrPhoneOrEmail(user.getUsername(),user.getPhone(),user.getEmail());//INI VALIDASI USER IS EXISTS
        try{
            if(!opUserResult.isEmpty()) //kondisi mengecek apakah user sudah terdaftar artinya user baru atau sudah ada
            {
                User nextUser = opUserResult.get();
                if(nextUser.getActive())//sudah terdaftar dan sudah aktif
                {
                    //NOTIFIKASI SAAT REGISTRASI BAGIAN MANA YANG SUDAH TERDAFTAR (USERNAME, EMAIL ATAU NOHP)
                    //kasus nya bisa saja user ingin memiliki 2 akun , namun dari sistem tidak memperbolehkan untuk duplikasi username,email atau no hp
                    //jika user ingin memiliki 2 akun , maka dia harus menggunakan username,email dan nohp yang berbeda dan belum terdaftar di sistem
                    /*
                        ex : username : paul, noHP : 628888888, email:paul@gmail.com lalu ingin mendaftar lagi dengan format
                        username : paul123, noHP : 6283333333, email:paul@gmail.com ,di kasus ini user harus menggunakan email lain walau username dan noHp sudah yang baru
                     */
                    if(nextUser.getEmail().equals(user.getEmail()))
                    {
                        return new ResponseHandler().generateResponse("EMAIL SUDAH TERDAFTAR !!",
                                HttpStatus.NOT_ACCEPTABLE,null,"FVRGS002",request);//EMAIL SUDAH TERDAFTAR DAN AKTIF
                    } else if (nextUser.getPhone().equals(user.getPhone())) {//FV = FAILED VALIDATION
                        return new ResponseHandler().generateResponse("NOMOR HP SUDAH TERDAFTAR !!",
                                HttpStatus.NOT_ACCEPTABLE,null,"FVRGS003",request);//NO HP SUDAH TERDAFTAR DAN AKTIF

                    } else if (nextUser.getUsername().equals(user.getUsername())) {
                        return new ResponseHandler().generateResponse("USERNAME SUDAH TERDAFTAR",
                                HttpStatus.NOT_ACCEPTABLE,null,"FVRGS004",request);//USERNAME SUDAH TERDAFTAR DAN AKTIF
                    } else {
                        /*
                            seharusnya tidak akan pernah masuk kesini karena dari query hanya 3 saja autentikasi nya yaitu :
                            username , email dan no HP
                         */
                        return new ResponseHandler().generateResponse("SEMUA BISA TERJADI BRO !!",
                                HttpStatus.NOT_ACCEPTABLE,null,"FVRGS005",request);//KARENA YANG DIAMBIL DATA YANG PERTAMA JADI ANGGAPAN NYA SUDAH TERDAFTAR SAJA
                    }
                } else {
                    /*
                        masuk kesini jika user sudah pernah melakukan registrasi (data sudah tersimpan ke tabel) namun tidak melanjutkan sampai selesai
                        flag isDelete = 0
                     */
                    nextUser.setPassword(BcryptImpl.hash(user.getPassword()+user.getUsername()));//ini trick nya agar tidak bisa di hash manual melalui database
                    nextUser.setToken(BcryptImpl.hash(String.valueOf(intVerification)));
                    nextUser.setUpdatedBy(nextUser.getUserID());
                    nextUser.setUpdatedAt(new Date());
                }
            } else {//user belum terdaftar sama sekali artinya user benar-benar baru menndaftar
                user.setPassword(BcryptImpl.hash(user.getPassword()+user.getUsername()));
                user.setToken(BcryptImpl.hash(String.valueOf(intVerification)));
                user.setCreatedAt(new Date());
                usersRepo.save(user);
            }

            String[] strVerify = new String[3];
            strVerify[0] = "Verifikasi Email";
            strVerify[1] = user.getName();
            strVerify[2] = String.valueOf(intVerification);

//            sBuild.setLength(0);
            /*
                method untuk kirim email
             */
            Thread first = new Thread(new Runnable() {
                @Override
                public void run() {
                    new ExecuteSMTP().sendSMTPToken(user.getEmail(),"TOKEN Verifikasi Email",strVerify,
                            "ver_regis.html");// \\data\\ver_regis
                    System.out.println("Email Terkirim");
                }
            });
            first.start();
        } catch (Exception e)
        {
//            strExceptionArr[1] = "checkRegis(User user, HttpServletRequest request)  --- LINE 130 \n ALL - REQUEST"+ RequestCapture.allRequest(request);
//            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLoging());
//            LogTable.inputLogRequest(strExceptionArr,e, OtherConfig.getFlagLogTable());
            return new ResponseHandler().generateResponse("GAGAL DIPROSES",HttpStatus.INTERNAL_SERVER_ERROR,null,"FERGS001",request);
        }

        return new ResponseHandler().generateResponse("TOKEN TERKIRIM",
                HttpStatus.CREATED,null,null,request);
    }

    public ResponseEntity<Object> doLogin(User userz, HttpServletRequest request) {
        /**
         *  KITA TIDAK TAHU KALAU USER MEMASUKKAN EMAIL, USERNAME ATAUPUN NO HP
         *  JADI APAPUN INPUTAN USER KITA MAPPING KE 3 FIELD DI DALAM OBJECT USER
         */
        userz.setUsername(userz.getUsername());
        userz.setEmail(userz.getUsername());
        userz.setPhone(userz.getUsername());
        Optional<User> opUserResult = usersRepo.findTop1ByUsernameOrPhoneOrEmail(userz.getUsername(),userz.getPhone(),userz.getEmail());//DATANYA PASTI HANYA 1
        User nextUser = null;
        try
        {
            if(!opUserResult.isEmpty())
            {
                nextUser = opUserResult.get();
                if(!BcryptImpl.verifyHash(userz.getPassword()+nextUser.getUsername(),nextUser.getPassword()))//dicombo dengan userName
                {
                    return new ResponseHandler().generateResponse("LOGIN GAGAL BRO / SIS !!",
                            HttpStatus.NOT_ACCEPTABLE,null,"FV01007",request);
                }
                Long intUserId = Long.parseLong(String.valueOf(nextUser.getUserID()));
                /**
                 * Ketiga Informasi ini kalau butuh dibuatan saja di Model User nya
                 * kalau digunakan pastikan flow nya di check lagi !!
                 */
//                nextUser.setLastLoginDate(new Date());
//                nextUser.setTokenCounter(0);//SETIAP KALI LOGIN BERHASIL , BERAPA KALIPUN UJI COBA REQUEST TOKEN YANG SEBELUMNYA GAGAL AKAN SECARA OTOMATIS DIRESET MENJADI 0
//                nextUser.setPasswordCounter(0);//SETIAP KALI LOGIN BERHASIL , BERAPA KALIPUN UJI COBA YANG SEBELUMNYA GAGAL AKAN SECARA OTOMATIS DIRESET MENJADI 0
                nextUser.setUpdatedBy(1L);
                nextUser.setUpdatedAt(new Date());
            }
            else
            {
                return new ResponseHandler().generateResponse("User Tidak Terdaftar",
                        HttpStatus.NOT_ACCEPTABLE,null,"FV01008",request);
            }
        }

        catch (Exception e)
        {
//            strExceptionArr[1]="doLogin(Userz userz,WebRequest request)  --- LINE 132";
//            LoggingFile.exceptionStringz(strExceptionArr,e, OtherConfig.getFlagLoging());
            return new ResponseHandler().generateResponse("Error",
                    HttpStatus.INTERNAL_SERVER_ERROR,null,"FE04003",request);
        }

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("token",authManager(nextUser,request));
        /*
            Transform dari List Menu Header
         */
        /*
            Transform dari List Menu Header
         */
        Access listAccess = nextUser.getAccess();
        List<Menu> listMenu = null;
        map.put("menu",new ArrayList<>());
        if (listAccess != null) {
            listMenu = nextUser.getAccess().getLtMenu();
//        List<MenuResponse3DTO> ltMenuDTO = modelMapper.map(listMenu,new TypeToken<List<MenuResponse3DTO>>(){}.getType());
            if (!listMenu.isEmpty()) {
                map.put("menu",new TransformationData().doTransformAksesMenuLogin(listMenu));
            }
        }
        return new ResponseHandler().generateResponse("Login Berhasil",
                HttpStatus.OK,map,null,request);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        String [] strArr = userName.split(OtherConfig.getFlagSplitToken());
//        if(strArr.length==2)
//        {
            /*
                berlaku jika fungsi yang memanggil sebelumnya sudah ada pengecekan ke database
                sehingga tidak 2x proses ke database
                mengurangi trafic untuk koneksi ke database
             */
//            return new org.springframework.security.core.userdetails.
//                    User(strArr[0],strArr[1],new ArrayList<>());
//        }

        /*
            WARNING !!
            username yang ada di parameter otomatis hanya username , bukan string yang di kombinasi dengan password atau informasi lainnya...
            userName yang ada di parameter belum tentu adalah username...
            karena sistem memperbolehkan login dengan email, nohp ataupun username
         */
        Optional<User> opUser = usersRepo.findTop1ByUsernameOrPhoneOrEmailAndIsActive(s,s,s,true);
        if(opUser.isEmpty())
        {
            return null;
        }
        User userNext = opUser.get();
         /*
            PARAMETER KE 3 TIDAK MENGGUNAKAN ROLE DARI SPRINGSECURITY CORE
            ROLE MODEL AKAN DITAMBAHKAN DI METHOD authManager DAN DIJADIKAN INFORMASI DI DALAM JWT
         */
        return new org.springframework.security.core.userdetails.
                User(userNext.getUsername(),userNext.getPassword(),new ArrayList<>());
    }

    public String authManager(User user, HttpServletRequest request)//RANGE 006-010
    {
        try {
            /* Untuk memasukkan user ke dalam */
            sBuild.setLength(0);
            UserDetails userDetails = loadUserByUsername(user.getUsername());
            if(userDetails==null)
            {
                return "FAILED";
            }

            /* Isi apapun yang perlu diisi ke dalam object mapz !! */
            mapz.put("uid",user.getUserID());
            mapz.put("ml",user.getEmail());//5-6-10
            mapz.put("pn",user.getPhone());//5-6-10
//        List<Menu> lMenu = user.getAkses().getListMenuAkses();
            Optional<User> optionalUser = usersRepo.findByUserIDAndAccess(user.getUserID(),
                    user.getAccess().getAccessID() == null ? 0 : user.getAccess().getAccessID());
            List<Menu> ltMenu = optionalUser.get().getAccess().getLtMenu();
            String strAppendMenu = "";
            sBuild.setLength(0);
            if(ltMenu.size()!=0)
            {
                for(int i=0;i<ltMenu.size();i++)
                {
                    sBuild.append(ltMenu.get(i).getMenuID()).append("-");
                }
                strAppendMenu = sBuild.toString();
                strAppendMenu = strAppendMenu.substring(0,strAppendMenu.length()-1);
                mapz.put("ln",strAppendMenu);//5-6-10
            } else {
                mapz.put("ln",new HashMap<>());//default semisal akses belum di set apa2 ke menu
            }
            String token = jwtUtility.generateToken(userDetails,mapz);
            token = Crypto.performEncrypt(token);

            return token;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
