package com.example.simpleplayer;

public class Songs {
    String title,id;
    Thumbnails songThumbnail;

    public Songs(String title, String id, Thumbnails songThumbnail) {
        this.title = title;
        this.id = id;
        this.songThumbnail = songThumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Thumbnails getSongThumbnail() {
        return songThumbnail;
    }

    public void setSongThumbnail(Thumbnails songThumbnail) {
        this.songThumbnail = songThumbnail;
    }

    static class Thumbnails {
        int width,height;
        String url;

        public Thumbnails(int width, int height, String url) {
            this.width = width;
            this.height = height;
            this.url = url;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
