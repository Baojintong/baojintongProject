package bao.jt.tong.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: Controller</p>
 * <p>Description: </p>
 * @author baojintong
 * @date 2017/12/27 23:35
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Controller")
@XmlType(propOrder = { "ControllerName", "Param" })
public class Controller  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String ControllerName;

    private List<String> Param;

    public String getControllerName() {
        return ControllerName;
    }

    public void setControllerName(String controllerName) {
        ControllerName = controllerName;
    }

    public List<String> getParam() {
        return Param;
    }

    public void setParam(List<String> param) {
        Param = param;
    }

    @Override
    public String toString() {
        return "Controller{" +
                "ControllerName='" + ControllerName + '\'' +
                ", Param=" + Param +
                '}';
    }
}
