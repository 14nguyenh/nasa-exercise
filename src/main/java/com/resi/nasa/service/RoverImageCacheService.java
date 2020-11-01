package com.resi.nasa.service;

import com.resi.nasa.model.Photo;
import com.resi.nasa.model.Photos;
import com.resi.nasa.model.entity.RoverImage;
import com.resi.nasa.model.repository.RoverImageRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoverImageCacheService
{
    final private RestTemplate restTemplate;
    //final private String API_KEY = "U3tfCOCb5DLfDYwigssrxkZDYKMUpfQloQbZHxDV";
    final private String API_KEY = "BQ8zKkZTwVOQ9Y3UVLNLMeFObr4nMEXAhf5eCw5h";
    final private String MARS_PHOTOS_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/";
    final private RoverImageRepository roverImageRepository;

    public RoverImageCacheService(RestTemplateBuilder restTemplateBuilder, RoverImageRepository roverImageRepository)
    {
        this.restTemplate = restTemplateBuilder.build();
        this.roverImageRepository = roverImageRepository;
    }

    public void downloadImagesFromFile(MultipartFile file)
    {
        List<String> dates = getDateStringsFromFile(file);

        List<String> imageUrls = getImageUrlsFromDates(dates);

        for(int i = 0; i < imageUrls.size(); i++)
        {
            System.out.println(imageUrls.get(i));
            downloadImageFromUrl(imageUrls.get(i));
        }
    }

    private void downloadImageFromUrl(String urlString)
    {
        try
        {
            URL url = new URL(urlString);
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1!=(n=in.read(buf)))
            {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();
            RoverImage roverImage = new RoverImage();
            roverImage.setImage(response);

            String extension = urlString.substring(urlString.lastIndexOf(".") + 1);

            roverImage.setFileType(extension.toLowerCase());

            roverImageRepository.save(roverImage);
        }
        catch (Exception ex)
        {
            System.out.println("Failed to downloadImageFromUrl: " + ex.getMessage());
        }
    }

    private List<String> getImageUrlsFromDates(List<String> dates)
    {
        List<String> imageUrls = new ArrayList<String>();
        for(int i = 0; i < dates.size(); i++)
        {
            Photos curiosityPhotos = getRoverImageDataOnDate("curiosity", dates.get(i));
            Photo[] curiosityPhotoArray = curiosityPhotos.getPhotos();
            for(int j = 0; j < curiosityPhotoArray.length; j++)
            {
                imageUrls.add(curiosityPhotoArray[j].getImgSrc().replaceFirst("http:", "https:"));
            }

            Photos opportunityPhotos = getRoverImageDataOnDate("opportunity", dates.get(i));
            Photo[] opportunityPhotoArray = opportunityPhotos.getPhotos();
            for(int j = 0; j < opportunityPhotoArray.length; j++)
            {
                imageUrls.add(opportunityPhotoArray[j].getImgSrc().replaceFirst("http:", "https:"));
            }

            Photos spiritPhotos = getRoverImageDataOnDate("spirit", dates.get(i));
            Photo[] spiritPhotoArray = spiritPhotos.getPhotos();
            for(int j = 0; j < spiritPhotoArray.length; j++)
            {
                imageUrls.add(spiritPhotoArray[j].getImgSrc().replaceFirst("http:", "https:"));
            }
        }

        return imageUrls;
    }

    public Photos getRoverImageDataOnDate(String rover, String date) {
        StringBuilder queryUrl = new StringBuilder(MARS_PHOTOS_URL);
        queryUrl.append(rover);
        queryUrl.append("/photos?earth_date=").append(date);
        queryUrl.append("&api_key=").append(API_KEY);
        return restTemplate.getForObject(queryUrl.toString(), Photos.class);
    }

    private List<String> getDateStringsFromFile(MultipartFile file)
    {
        try
        {
            //Split file into string array
            String[] text = (new String(file.getBytes())).split("\n");

            List<SimpleDateFormat> patternsToMatch = new ArrayList<SimpleDateFormat>();
            patternsToMatch.add(new SimpleDateFormat("MM-dd-yy"));
            patternsToMatch.add(new SimpleDateFormat("MM/dd/yy"));
            patternsToMatch.add(new SimpleDateFormat("MM-dd-yyyy"));
            patternsToMatch.add(new SimpleDateFormat("MM/dd/yyyy"));
            patternsToMatch.add(new SimpleDateFormat("MMMMM dd, yyyy"));
            patternsToMatch.add(new SimpleDateFormat("MMM dd, yyyy"));
            patternsToMatch.add(new SimpleDateFormat("MMM-dd-yyyy"));
            patternsToMatch.add(new SimpleDateFormat("MMM-dd-yy"));
            patternsToMatch.add(new SimpleDateFormat("MMM/dd/yyyy"));
            patternsToMatch.add(new SimpleDateFormat("MMM/dd/yyyy"));

            //Parse string array into date array
            List<Date> dates = new ArrayList<Date>();
            for(int i = 0; i < text.length; i++)
            {
                for (SimpleDateFormat pattern : patternsToMatch) {
                    try
                    {
                        dates.add(new Date(pattern.parse(text[i]).getTime()));
                        break;
                    } catch (ParseException pe) {}
                }
            }

            SimpleDateFormat outputDatePattern = new SimpleDateFormat("yyyy-MM-dd");

            List<String> dateStrings = new ArrayList<String>();
            for(int i = 0; i < dates.size(); i++)
            {
                dateStrings.add(outputDatePattern.format(dates.get(i)));
            }

            return dateStrings;
        }
        catch (Exception ex)
        {
            System.out.println("Exception reading input file: " + ex.getMessage());
            return new ArrayList<String>();
        }
    }
}
