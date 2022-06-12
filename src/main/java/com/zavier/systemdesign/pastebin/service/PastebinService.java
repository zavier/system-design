package com.zavier.systemdesign.pastebin.service;

import com.zavier.systemdesign.pastebin.dao.PasteDao;
import com.zavier.systemdesign.pastebin.domain.Paste;
import com.zavier.systemdesign.pastebin.util.Base62Utils;
import com.zavier.systemdesign.pastebin.util.Md5Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class PastebinService {

    @Resource
    private PasteDao pasteDao;

    @Transactional(rollbackFor = Exception.class)
    public String calcAndRecordShortLink(String pastePath) {
        final Paste paste = new Paste();
        paste.setCreateTime(new Date());
        paste.setPastePath(pastePath);
        pasteDao.save(paste);

        // TODO 处理唯一索引冲突情况
        final String link = generateShortLink();
        paste.setShortLink(link);
        pasteDao.save(paste);

        return link;
    }

    public String getRealPathByShortLink(String shortLink) {
        // TODO 异常处理
        final Paste paste = pasteDao.findByShortLink(shortLink);
        return paste.getPastePath();
    }

    public String generateShortLink() {
        // 16 * 8 = 128
        final byte[] bytes = Md5Utils.md5(UUID.randomUUID().toString());
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bytes.length; i += 4) {
            long v1 = (long) (bytes[i] & 0xff) << 24;
            long v2 = (bytes[i + 1] & 0xff) << 16;
            long v3 = (bytes[i + 2] & 0xff) << 8;
            long v4 = (bytes[i + 3] & 0xff);

            long v = v1 + v2 + v3 + v4;
            builder.append(Base62Utils.base62(v));
        }
        return builder.substring(0, 7);
    }

}
