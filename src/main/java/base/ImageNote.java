package base;

import java.io.File;
import java.io.Serial;

public class ImageNote extends Note {
  @Serial
  private static final long serialVersionUID = 7994861827768973392L;

  public File image;

  public ImageNote(String title) {
    super(title);
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + ':' + super.toString();
  }
}
