package base;

import java.util.Arrays;
import java.util.Date;

public class Note implements Comparable<Note> {
  private Date date;

  private String title;

  public Note(String title) {
    this.date = new Date();
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof Note)) // if (obj == null || !getClass().equals(obj.getClass()))
      return false;
    Note obj2 = (Note) obj;
    if (!title.equals(obj2.title))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(new Object[] { title });
  }

  @Override
  public int compareTo(Note o) {
    return -date.compareTo(o.date);
  }
}
