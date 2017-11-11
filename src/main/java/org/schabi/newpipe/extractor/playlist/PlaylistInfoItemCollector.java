package org.schabi.newpipe.extractor.playlist;

import org.schabi.newpipe.extractor.InfoItemCollector;
import org.schabi.newpipe.extractor.exceptions.ParsingException;

public class PlaylistInfoItemCollector extends InfoItemCollector {
    public PlaylistInfoItemCollector(int serviceId) {
        super(serviceId);
    }

    public PlaylistInfoItem extract(PlaylistInfoItemExtractor extractor) throws ParsingException {

        String name = extractor.getName();
        int serviceId = getServiceId();
        String url = extractor.getUrl();

        PlaylistInfoItem resultItem = new PlaylistInfoItem(serviceId, url, name);

        try {
            resultItem.setUploaderName(extractor.getUploaderName());
        } catch (Exception e) {
            addError(e);
        }
        try {
            resultItem.setThumbnailUrl(extractor.getThumbnailUrl());
        } catch (Exception e) {
            addError(e);
        }
        try {
            resultItem.setStreamCount(extractor.getStreamCount());
        } catch (Exception e) {
            addError(e);
        }
        return resultItem;
    }

    public void commit(PlaylistInfoItemExtractor extractor) throws ParsingException {
        try {
            addItem(extract(extractor));
        } catch (Exception e) {
            addError(e);
        }
    }
}
