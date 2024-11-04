package com.example.bookapp1.Dtos;

public class CategoryButtonData {

        private final int id;
        private final String text;

        public CategoryButtonData(int id, String text) {
            this.id = id;
            this.text = text;
        }

        public int getId() {
            return id;
        }

        public String getText() {
            return text;
        }


}
