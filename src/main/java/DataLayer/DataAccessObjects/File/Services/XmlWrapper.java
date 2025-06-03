package DataLayer.DataAccessObjects.File.Services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
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
