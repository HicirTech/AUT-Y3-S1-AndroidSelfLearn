package com.londonappbrewery.destini;

import android.content.res.Resources;

public class Story {

    public int getStoryID() {
        return storyID;
    }

    public void setStoryID(int storyID) {
        this.storyID = storyID;
    }

    private String story;
    private int storyID;

    Story(int storyID)
    {
        this.setStoryID(storyID);
    }


}
