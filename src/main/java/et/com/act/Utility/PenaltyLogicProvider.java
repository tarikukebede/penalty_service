package et.com.act.Utility;

import et.com.act.Logics.*;
import et.com.act.Services.PenaltyCalculator;

import javax.ejb.Stateless;

@Stateless
public class PenaltyLogicProvider {

    public PenaltyCalculator getPenaltyService(String logicKey){
        switch (logicKey){
            case Logic.YARDSTICK:
                return new Yardstick();
            case Logic.MANKUL:
                return new Mankul();
            case Logic.ADDIS_ABAY:
                return new AddisAbay();
            case Logic.BEGEMEDER:
                return new Begemeder();
            case Logic.SELECT:
                return new Select();
            default:
                return new Default();
        }
    }
}
