package com.zavier.systemdesign.pastebin.web.param;

import lombok.Data;

@Data
public class PasteRequestVo {

    private Integer expirationLengthInMinutes;

    private String pasteContents;
}
