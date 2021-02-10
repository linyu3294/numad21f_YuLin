package com.gremlinweekend.numad21s_yulin.linkActivity;

public class LinkItem implements ILink
{

    private final int linkImage;
    private final String linkURL;
    private boolean isModifying;



    public LinkItem(int linkImage, String linkURL, boolean isModifying) {
        this.linkImage = linkImage;
        this.linkURL = linkURL;
        this.isModifying = isModifying;
    }

    public int getLinkImage() {
        return linkImage;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public boolean isModifying() {
        return isModifying;
    }

    @Override
    public void onLinkClick(int position) {
        isModifying =!isModifying;
    }

    @Override
    public void onlinkSaveClick(int position) {
        isModifying =!isModifying;
    }
}
