package com.example.contact.component;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponent;
import com.example.contact.component.fragment.ContactFragment;

public class ContactComponent implements IComponent {
    @Override
    public String getName() {
        return "Contact";
    }

    @Override
    public boolean onCall(CC cc) {

        String actionName = cc.getActionName();
        switch (actionName) {
            case "getContractFragment":
                CCResult ccResult = new CCResult();
                ccResult.addData("fragment", new ContactFragment());
                cc.sendCCResult(cc.getCallId(), ccResult);
                return true;
            default:
                CC.sendCCResult(cc.getCallId(), CCResult.errorUnsupportedActionName());
        }
        return false;
    }
}
