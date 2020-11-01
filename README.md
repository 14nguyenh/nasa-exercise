# nasa-exercise

This is my implementation of the exercise described at https://github.com/hawkescom/marsrover

I decided to use Spring Boot. I am not an expert at it (yet) so there is a lot of java plumbing that could most likely be improved (the action of downloading the images). It also takes a significant amount of time to download all of the images (upwards of a few minutes) and my application blocks on this step. The user experience can be improved by using a message queue such as RabbitMQ to send a request to download the images, and redirect the user back instantly. The user would still have to wait for all the images to download, but they would be able to do other things while waiting. If I were to use RabbitMQ, I could also improve performance by multithreading the downloads, however that would only be a linear scale performance increase.

I noticed that with my primitive way of downloading these images, I ran into issues with the img_src URLs when using a non-secure scheme (http:// instead of https://) because they redirected to the secure URL. As a work-around, I forced all the URLs to have a scheme of 'https://'.

I also opted to store the images in a database, and served them up directly from there. I was asked if I knew about Spring Data and so I implemented this with h2 as a proof-of-concept to showcase that I did, but given the problem statement it would have been much easier to just download and store the files on disk in a static directory and serve them from there.

I am also not too familiar with Docker (I plan on taking a Docker course), but I was able to create a tomcat docker container and deploy the webapp by copying the .war file to the webapps directory.

I've included my .idea project directory for Intellij. I am not sure if that copies my run configurations.

Lastly, I hard-coded my Nasa API key. I wasn't sure what the best way of making this configurable was. I could work it into a properties file somehow or I could have exposed it as a setting on the webapp so that the user could configure it but for simplicity it is hard-coded.
