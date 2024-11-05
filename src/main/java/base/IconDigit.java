package base;

import java.io.Serial;

public class IconDigit extends Icon {
  @Serial
  private static final long serialVersionUID = -7768068445692925952L;

  public IconDigit(char base) {
    super(base);
  }

  @Override
  char encircle(char c) {
    return (char) (c - '0' + '①');
  }
}
