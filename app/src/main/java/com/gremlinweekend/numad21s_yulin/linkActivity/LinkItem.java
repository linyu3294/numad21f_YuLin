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



    @Override
    public void onLinkClick(int position) {
        isModifying =!isModifying;
    }

    @Override
    public String onclickNavigate(int position) {
        return this.linkURL;
    }
}
