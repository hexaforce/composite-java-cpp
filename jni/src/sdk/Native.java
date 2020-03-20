package sdk;

import java.io.File;

public enum Native {

	lib(new File("")), //

	linux(new File("native/linux_x86_64/libnative.so")), //

	mac(new File("native/macos/libnative.dylib")), //

	windows(new File("native/win32/native.dll"));

	final public File binary;

	Native(File binary) {
		this.binary = binary;
	}

	public String path() {
		String osName = System.getProperty("os.name").toLowerCase();
		for (Native n : values())
			if (osName.startsWith(n.name()))
				return n.binary.getAbsolutePath();
		return null;
	}

}
