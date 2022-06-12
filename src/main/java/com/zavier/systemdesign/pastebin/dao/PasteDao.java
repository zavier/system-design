package com.zavier.systemdesign.pastebin.dao;

import com.zavier.systemdesign.pastebin.domain.Paste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasteDao extends JpaRepository<Paste, Long> {

    Paste findByShortLink(String shortLink);
}
