package com.zavier.systemdesign.pastebin.web;

import com.zavier.systemdesign.pastebin.service.PastebinService;
import com.zavier.systemdesign.pastebin.web.param.PasteRequestVo;
import com.zavier.systemdesign.pastebin.web.param.PasteResponseVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PastebinController {

    @Resource
    private PastebinService pastebinService;

    @PostMapping("/paste/save")
    @ResponseBody
    public PasteResponseVo save(@RequestBody PasteRequestVo content) {
        // TODO 参数校验及异常响应
        final String shortLink = pastebinService.calcAndRecordShortLink(content.getPasteContents());
        final PasteResponseVo responseVo = new PasteResponseVo();
        responseVo.setShortlink("https://zhengw-tech.com/t/" + shortLink);
        return responseVo;
    }

    @GetMapping("/t/{shortlink}")
    public void redirect2RealAddress(HttpServletResponse response, @PathVariable("shortlink") String shortlink) throws IOException {
        final String realPathByShortLink = pastebinService.getRealPathByShortLink(shortlink);
        response.sendRedirect(realPathByShortLink);
    }
}
