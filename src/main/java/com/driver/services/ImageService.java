package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();


        Image image= new Image();
        image.setId(blogId);
        image.setDescription(description);
        image.setDimensions(dimensions);

        blog.getImageList().add(image);

        blogRepository2.save(blog);
        imageRepository2.save(image);
        return image;

    }

    public void deleteImage(Integer id){
        if(imageRepository2.findById(id).isPresent()){
            imageRepository2.deleteById(id);
        }
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        int count = 0;
        Image image = imageRepository2.findById(id).get();
        String [] scrarray = screenDimensions.split("X"); //A=Length   X    B=Breadth
//        if(!imageRepository2.findById(id).isPresent()){
//            throw new Exception();
//        }

        String imageDimensions = image.getDimensions();
        String [] imgArr= imageDimensions.split("X");

        int screenLength = Integer.parseInt(scrarray[0]); //A -- > integer
        int scrBreadth = Integer.parseInt(scrarray[1]); //B -- > integer

        int imgLength = Integer.parseInt(imgArr[0]); //A -- > integer
        int imgBreadth = Integer.parseInt(imgArr[1]); //B -- > integer

        int length1 = screenLength/imgLength;
        int length2 = scrBreadth/imgBreadth;
        count = length1*length2;


        return count;

    }
}
