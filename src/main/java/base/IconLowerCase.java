package base;

import java.io.Serial;

public class IconLowerCase extends Icon {
	@Serial
	private static final long serialVersionUID = 2568793881797485380L;

	public IconLowerCase(char base) {
		super(base);
	}

	@Override
	char encircle(char c) {
		return (char) (c - 'a' + 'ⓐ');
	}
}
