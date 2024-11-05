package base;

import java.io.Serial;
import java.io.Serializable;

public abstract class Icon implements Serializable {
  @Serial
  private static final long serialVersionUID = -8318786117734005345L;

  final char base;

  public Icon(char base) {
    this.base = encircle(base);
  }

  abstract char encircle(char c);

  @Override
  public String toString() {
    return "" + base;
  }
}
