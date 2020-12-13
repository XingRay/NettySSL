//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.netty.internal.tcnative;

import java.io.File;

public final class Library {
    private static final String[] NAMES = new String[]{"netty_tcnative", "libnetty_tcnative"};
    private static final String PROVIDED = "provided";
    private static Library _instance = null;

    private Library() throws Exception {
        boolean loaded = false;
        String path = System.getProperty("java.library.path");
        String[] paths = path.split(File.pathSeparator);
        StringBuilder err = new StringBuilder();

        for(int i = 0; i < NAMES.length; ++i) {
            try {
                loadLibrary(NAMES[i]);
                loaded = true;
            } catch (ThreadDeath var10) {
                throw var10;
            } catch (VirtualMachineError var11) {
                throw var11;
            } catch (Throwable var12) {
                String name = System.mapLibraryName(NAMES[i]);

                for(int j = 0; j < paths.length; ++j) {
                    File fd = new File(paths[j], name);
                    if (fd.exists()) {
                        throw new RuntimeException(var12);
                    }
                }

                if (i > 0) {
                    err.append(", ");
                }

                err.append(var12.getMessage());
            }

            if (loaded) {
                break;
            }
        }

        if (!loaded) {
            throw new UnsatisfiedLinkError(err.toString());
        }
    }

    private Library(String libraryName) {
        if (!"provided".equals(libraryName)) {
            loadLibrary(libraryName);
        }

    }

    private static void loadLibrary(String libraryName) {
        System.loadLibrary(calculatePackagePrefix().replace('.', '_') + libraryName);
    }

    private static String calculatePackagePrefix() {
        String maybeShaded = Library.class.getName();
        String expected = "io!netty!internal!tcnative!Library".replace('!', '.');
        if (!maybeShaded.endsWith(expected)) {
            throw new UnsatisfiedLinkError(String.format("Could not find prefix added to %s to get %s. When shading, only adding a package prefix is supported", expected, maybeShaded));
        } else {
            return maybeShaded.substring(0, maybeShaded.length() - expected.length());
        }
    }

    private static native boolean initialize0();

    private static native boolean aprHasThreads();

    private static native int aprMajorVersion();

    private static native String aprVersionString();

    public static boolean initialize() throws Exception {
        return initialize("provided", (String)null);
    }

    public static boolean initialize(String libraryName, String engine) throws Exception {
        if (_instance == null) {
            _instance = libraryName == null ? new Library() : new Library(libraryName);
            if (aprMajorVersion() < 1) {
                throw new UnsatisfiedLinkError("Unsupported APR Version (" + aprVersionString() + ")");
            }

            if (!aprHasThreads()) {
                throw new UnsatisfiedLinkError("Missing APR_HAS_THREADS");
            }
        }

        return initialize0() && SSL.initialize(engine) == 0;
    }
}
