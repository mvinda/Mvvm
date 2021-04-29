package com.example.news.component;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponent;
import com.example.news.headlinenews.HeadlineNewsFragment;

public class NewComponent implements IComponent {
    @Override
    public String getName() {
        return "News";
    }

    @Override
    public boolean onCall(CC cc) {

        String actionName = cc.getActionName();
        switch (actionName){
            case "getHeadlineNewsFragment":
                CCResult ccResult = new CCResult();
                ccResult.addData("fragment",new HeadlineNewsFragment());
                cc.sendCCResult(cc.getCallId(),ccResult);
                return true;
             default:
                 CC.sendCCResult(cc.getCallId(),CCResult.errorUnsupportedActionName());
        }
        return false;
    }
}
