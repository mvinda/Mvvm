package com.example.component;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponent;
import com.example.fragment.AccountFragment;

public class AccountComponent implements IComponent {
    @Override
    public String getName() {
        return "Account";
    }

    @Override
    public boolean onCall(CC cc) {

        String actionName = cc.getActionName();
        switch (actionName){
            case "getAccountPage":
                CCResult ccResult = new CCResult();
                ccResult.addData("fragment",new AccountFragment());
                cc.sendCCResult(cc.getCallId(),ccResult);
                return true;
             default:
                 CC.sendCCResult(cc.getCallId(),CCResult.errorUnsupportedActionName());
        }
        return false;
    }
}
