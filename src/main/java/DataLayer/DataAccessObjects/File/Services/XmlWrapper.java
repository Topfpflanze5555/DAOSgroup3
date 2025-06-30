package DataLayer.DataAccessObjects.File.Services;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlWrapper<T> {

  @XmlAnyElement
  private List<T> items;

  public XmlWrapper()
  {
  }

  public XmlWrapper(List<T> items)
  {
    this.items = items;
  }

  public List<T> getItems()
  {
    return items;
  }

  public void setItems(final List<T> items)
  {
    this.items = items;
  }
}
