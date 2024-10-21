package base;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.IntStream;

public class Note implements Comparable<Note> {
  private Date date;

  private String title;

  static long counter = 1L;

  public Note(String title) {
    this.date = new Date(counter++);
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof Note obj2)) // if (obj == null || !getClass().equals(obj.getClass()))
      return false;
    return title.equals(obj2.title);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(new Object[] { title });
  }

  @Override
  public int compareTo(Note o) {
    Class<?>[] classOrder = { TextNote.class, ImageNote.class };
    int classCompareTo = IntStream.range(0, classOrder.length).filter(idx -> classOrder[idx].isInstance(this))
        .findFirst().orElse(-1)
        - IntStream.range(0, classOrder.length).filter(idx -> classOrder[idx].isInstance(o)).findFirst().orElse(-1);
    if (classCompareTo != 0)
      return classCompareTo;
    return title.compareTo(o.title);
  }

  @Override
  public String toString() {
    return date + "\t" + title;
  }
}
