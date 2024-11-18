package base;

import java.io.Serial;

public class IconUpperCase extends Icon {
	@Serial
	private static final long serialVersionUID = -7388957755416252215L;

	public IconUpperCase(char base) {
		super(base);
	}

	@Override
	char encircle(char c) {
		return (char) (c - 'A' + 'Ⓐ');
	}
}
