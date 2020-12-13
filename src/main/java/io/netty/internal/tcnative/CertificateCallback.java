//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.netty.internal.tcnative;

public interface CertificateCallback {
    byte TLS_CT_RSA_SIGN = 1;
    byte TLS_CT_DSS_SIGN = 2;
    byte TLS_CT_RSA_FIXED_DH = 3;
    byte TLS_CT_DSS_FIXED_DH = 4;
    byte TLS_CT_ECDSA_SIGN = 64;
    byte TLS_CT_RSA_FIXED_ECDH = 65;
    byte TLS_CT_ECDSA_FIXED_ECDH = 66;

    void handle(long var1, byte[] var3, byte[][] var4) throws Exception;
}
