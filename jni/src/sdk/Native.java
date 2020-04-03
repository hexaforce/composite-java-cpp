package sdk;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public enum Native {

	lib(new File("")), //

	linux(new File("native/linux_x86_64/libnative.so")), //

	mac(new File("lib/native/macos/libnative.dylib")), //

	windows(new File("native/win32/native.dll"));

	final public File binary;

	Native(File binary) {
		this.binary = binary;
	}

	public String path() {
		String osName = System.getProperty("os.name").toLowerCase();
		for (Native n : values())
			if (osName.startsWith(n.name())) {
				if (!n.binary.exists()) {
					InputStream in = ClassLoader.getSystemResourceAsStream(n.binary.getPath());
					try {
						Files.copy(in, n.binary.toPath());
					} catch (IOException e) {
						System.err.println(e.getMessage());
					}
				}
				return n.binary.getAbsolutePath();
			}
		return null;
	}

}
