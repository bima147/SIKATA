package coid.bcafinance.bpsringbootfinal.constant;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 04/03/2024 14:14
@Last Modified 04/03/2024 14:14
Version 1.0
*/

public class ScriptDBSQLServer {
    public static final String FK_CHILD_TO_PARENT = "FOREIGN KEY ([IDParent]) REFERENCES [bcafBatch3].[MstParent] ([idParent]) ON DELETE SET NULL ON UPDATE SET NULL";
}
