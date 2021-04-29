package com.example.video.component;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponent;
import com.example.video.fragment.VideoFragment;

public class VideoComponent implements IComponent {
    @Override
    public String getName() {
        return "Video";
    }

    @Override
    public boolean onCall(CC cc) {

        String actionName = cc.getActionName();
        switch (actionName){
            case "getVideoFragment":
                CCResult ccResult = new CCResult();
                ccResult.addData("fragment",new VideoFragment());
                cc.sendCCResult(cc.getCallId(),ccResult);
                return true;
             default:
                 CC.sendCCResult(cc.getCallId(),CCResult.errorUnsupportedActionName());
        }
        return false;
    }
}
