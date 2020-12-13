//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.netty.internal.tcnative;

/** @deprecated */
@Deprecated
public interface CertificateRequestedCallback {
    byte TLS_CT_RSA_SIGN = 1;
    byte TLS_CT_DSS_SIGN = 2;
    byte TLS_CT_RSA_FIXED_DH = 3;
    byte TLS_CT_DSS_FIXED_DH = 4;
    byte TLS_CT_ECDSA_SIGN = 64;
    byte TLS_CT_RSA_FIXED_ECDH = 65;
    byte TLS_CT_ECDSA_FIXED_ECDH = 66;

    void requested(long var1, long var3, long var5, byte[] var7, byte[][] var8) throws Exception;
}
