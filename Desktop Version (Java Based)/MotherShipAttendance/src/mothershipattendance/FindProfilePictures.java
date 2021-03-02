/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.util.ArrayList;

/**
 *
 * @author tylerfontana
 */
public class FindProfilePictures {
    
    public String imageLarge;
    public String imageMedium;
    public String imageSmall;
    public String imageXS;
    
    public FindProfilePictures() {
        
    }
    
    public String[] getImageIcons(String path) {
        
        System.out.println(path);
        
        if (path.contains("ProfilePicture1Scaled")) {
            imageLarge = "/mothershipattendance/Image/ProfilePictures/Large/ProfilePicture1.png";
            imageMedium = "/mothershipattendance/Image/ProfilePictures/Scaled/ProfilePicture1Scaled.png";
            imageSmall = "/mothershipattendance/Image/ProfilePictures/Small/ProfilePicture1Small.png";
            imageXS = "/mothershipattendance/Image/ProfilePictures/XSChatIcons/ProfilePictureXS1.png";
        }
        else if (path.contains("ProfilePicture2Scaled")) {
            imageLarge = "/mothershipattendance/Image/ProfilePictures/Large/ProfilePicture2.png";
            imageMedium = "/mothershipattendance/Image/ProfilePictures/Scaled/ProfilePicture2Scaled.png";
            imageSmall = "/mothershipattendance/Image/ProfilePictures/Small/ProfilePicture2Small.png";
            imageXS = "/mothershipattendance/Image/ProfilePictures/XSChatIcons/ProfilePictureXS2.png";
        }
        else if (path.contains("ProfilePicture3Scaled")) {
            imageLarge = "/mothershipattendance/Image/ProfilePictures/Large/ProfilePicture3.png";
            imageMedium = "/mothershipattendance/Image/ProfilePictures/Scaled/ProfilePicture3Scaled.png";
            imageSmall = "/mothershipattendance/Image/ProfilePictures/Small/ProfilePicture3Small.png";
            imageXS = "/mothershipattendance/Image/ProfilePictures/XSChatIcons/ProfilePictureXS3.png";
        }
        else if (path.contains("ProfilePicture4Scaled")) {
            imageLarge = "/mothershipattendance/Image/ProfilePictures/Large/ProfilePicture4.png";
            imageMedium = "/mothershipattendance/Image/ProfilePictures/Scaled/ProfilePicture4Scaled.png";
            imageSmall = "/mothershipattendance/Image/ProfilePictures/Small/ProfilePicture4Small.png";
            imageXS = "/mothershipattendance/Image/ProfilePictures/XSChatIcons/ProfilePictureXS4.png";
        }
        else if (path.contains("ProfilePicture5Scaled")) {
            imageLarge = "/mothershipattendance/Image/ProfilePictures/Large/ProfilePicture5.png";
            imageMedium = "/mothershipattendance/Image/ProfilePictures/Scaled/ProfilePicture5Scaled.png";
            imageSmall = "/mothershipattendance/Image/ProfilePictures/Small/ProfilePicture5Small.png";
            imageXS = "/mothershipattendance/Image/ProfilePictures/XSChatIcons/ProfilePictureXS5.png";
        }
        else if (path.contains("ProfilePicture6Scaled")) {
            imageLarge = "/mothershipattendance/Image/ProfilePictures/Large/ProfilePicture6.png";
            imageMedium = "/mothershipattendance/Image/ProfilePictures/Scaled/ProfilePicture6Scaled.png";
            imageSmall = "/mothershipattendance/Image/ProfilePictures/Small/ProfilePicture6Small.png";
            imageXS = "/mothershipattendance/Image/ProfilePictures/XSChatIcons/ProfilePictureXS6.png";
        }
        else if (path.contains("ProfilePicture7Scaled")) {
            imageLarge = "/mothershipattendance/Image/ProfilePictures/Large/ProfilePicture7.png";
            imageMedium = "/mothershipattendance/Image/ProfilePictures/Scaled/ProfilePicture7Scaled.png";
            imageSmall = "/mothershipattendance/Image/ProfilePictures/Small/ProfilePicture7Small.png";
            imageXS = "/mothershipattendance/Image/ProfilePictures/XSChatIcons/ProfilePictureXS7.png";
        }
        else if (path.contains("ProfilePicture8Scaled")) {
            imageLarge = "/mothershipattendance/Image/ProfilePictures/Large/ProfilePicture8.png";
            imageMedium = "/mothershipattendance/Image/ProfilePictures/Scaled/ProfilePicture8Scaled.png";
            imageSmall = "/mothershipattendance/Image/ProfilePictures/Small/ProfilePicture8Small.png";
            imageXS = "/mothershipattendance/Image/ProfilePictures/XSChatIcons/ProfilePictureXS8.png";
        }
        else if (path.contains("ProfilePicture9Scaled")) {
            imageLarge = "/mothershipattendance/Image/ProfilePictures/Large/ProfilePicture9.png";
            imageMedium = "/mothershipattendance/Image/ProfilePictures/Scaled/ProfilePicture9Scaled.png";
            imageSmall = "/mothershipattendance/Image/ProfilePictures/Small/ProfilePicture9Small.png";
            imageXS = "/mothershipattendance/Image/ProfilePictures/XSChatIcons/ProfilePictureXS9.png";
        }
        else if (path.contains("ProfilePicture10Scaled")) {
            imageLarge = "/mothershipattendance/Image/ProfilePictures/Large/ProfilePicture10.png";
            imageMedium = "/mothershipattendance/Image/ProfilePictures/Scaled/ProfilePicture10Scaled.png";
            imageSmall = "/mothershipattendance/Image/ProfilePictures/Small/ProfilePicture10Small.png";
            imageXS = "/mothershipattendance/Image/ProfilePictures/XSChatIcons/ProfilePictureXS10.png";
        }
        else if (path.contains("ProfilePicture11Scaled")) {
            imageLarge = "/mothershipattendance/Image/ProfilePictures/Large/ProfilePicture11.png";
            imageMedium = "/mothershipattendance/Image/ProfilePictures/Scaled/ProfilePicture11Scaled.png";
            imageSmall = "/mothershipattendance/Image/ProfilePictures/Small/ProfilePicture11Small.png";
            imageXS = "/mothershipattendance/Image/ProfilePictures/XSChatIcons/ProfilePictureXS11.png";
        }
        else if (path.contains("ProfilePicture12Scaled")) {
            imageLarge = "/mothershipattendance/Image/ProfilePictures/Large/ProfilePicture12.png";
            imageMedium = "/mothershipattendance/Image/ProfilePictures/Scaled/ProfilePicture12Scaled.png";
            imageSmall = "/mothershipattendance/Image/ProfilePictures/Small/ProfilePicture12Small.png";
            imageXS = "/mothershipattendance/Image/ProfilePictures/XSChatIcons/ProfilePictureXS12.png";
        }
        else if (path.contains("ProfilePicture13Scaled")) {
            imageLarge = "/mothershipattendance/Image/ProfilePictures/Large/ProfilePicture13.png";
            imageMedium = "/mothershipattendance/Image/ProfilePictures/Scaled/ProfilePicture13Scaled.png";
            imageSmall = "/mothershipattendance/Image/ProfilePictures/Small/ProfilePicture13Small.png";
            imageXS = "/mothershipattendance/Image/ProfilePictures/XSChatIcons/ProfilePictureXS13.png";
        }
        else if (path.contains("ProfilePicture14Scaled")) {
            imageLarge = "/mothershipattendance/Image/ProfilePictures/Large/ProfilePicture14.png";
            imageMedium = "/mothershipattendance/Image/ProfilePictures/Scaled/ProfilePicture14Scaled.png";
            imageSmall = "/mothershipattendance/Image/ProfilePictures/Small/ProfilePicture14Small.png";
            imageXS = "/mothershipattendance/Image/ProfilePictures/XSChatIcons/ProfilePictureXS14.png";
        }
        else if (path.contains("ProfilePicture15Scaled")) {
            imageLarge = "/mothershipattendance/Image/ProfilePictures/Large/ProfilePicture15.png";
            imageMedium = "/mothershipattendance/Image/ProfilePictures/Scaled/ProfilePicture15Scaled.png";
            imageSmall = "/mothershipattendance/Image/ProfilePictures/Small/ProfilePicture15Small.png";
            imageXS = "/mothershipattendance/Image/ProfilePictures/XSChatIcons/ProfilePictureXS15.png";
        }
        else if (path.contains("ProfilePicture16Scaled")) {
            imageLarge = "/mothershipattendance/Image/ProfilePictures/Large/ProfilePicture16.png";
            imageMedium = "/mothershipattendance/Image/ProfilePictures/Scaled/ProfilePicture16Scaled.png";
            imageSmall = "/mothershipattendance/Image/ProfilePictures/Small/ProfilePicture16Small.png";
            imageXS = "/mothershipattendance/Image/ProfilePictures/XSChatIcons/ProfilePictureXS16.png";
        }
        else if (path.contains("ProfilePicture17Scaled")) {
            imageLarge = "/mothershipattendance/Image/ProfilePictures/Large/ProfilePicture17.png";
            imageMedium = "/mothershipattendance/Image/ProfilePictures/Scaled/ProfilePicture17Scaled.png";
            imageSmall = "/mothershipattendance/Image/ProfilePictures/Small/ProfilePicture17Small.png";
            imageXS = "/mothershipattendance/Image/ProfilePictures/XSChatIcons/ProfilePictureXS17.png";
        }
        else if (path.contains("ProfilePicture18Scaled")) {
            imageLarge = "/mothershipattendance/Image/ProfilePictures/Large/ProfilePicture18.png";
            imageMedium = "/mothershipattendance/Image/ProfilePictures/Scaled/ProfilePicture18Scaled.png";
            imageSmall = "/mothershipattendance/Image/ProfilePictures/Small/ProfilePicture18Small.png";
            imageXS = "/mothershipattendance/Image/ProfilePictures/XSChatIcons/ProfilePictureXS18.png";
        }
        else if (path.contains("ProfilePicture19Scaled")) {
            imageLarge = "/mothershipattendance/Image/ProfilePictures/Large/ProfilePicture19.png";
            imageMedium = "/mothershipattendance/Image/ProfilePictures/Scaled/ProfilePicture19Scaled.png";
            imageSmall = "/mothershipattendance/Image/ProfilePictures/Small/ProfilePicture19Small.png";
            imageXS = "/mothershipattendance/Image/ProfilePictures/XSChatIcons/ProfilePictureXS19.png";
        }
        else if (path.contains("ProfilePicture20Scaled")) {
            imageLarge = "/mothershipattendance/Image/ProfilePictures/Large/ProfilePicture20.png";
            imageMedium = "/mothershipattendance/Image/ProfilePictures/Scaled/ProfilePicture20Scaled.png";
            imageSmall = "/mothershipattendance/Image/ProfilePictures/Small/ProfilePicture20Small.png";
            imageXS = "/mothershipattendance/Image/ProfilePictures/XSChatIcons/ProfilePictureXS20.png";
        }
        
        String[] imageList = {imageLarge, imageMedium, imageSmall, imageXS};
        
        return imageList;
    }
}
