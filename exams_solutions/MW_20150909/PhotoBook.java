/**
 Note: I assume the only way to remove a photo from the array is by uploading
 the photobook
**/
public class PhotoBook{
  int nextFreeSlot;
  int maxCapacity;
  String bookTitle;
  Photo[] photos;
  String[] captions;

  public PhotoBook(int maxCapacity, String bookTitle){
    this.maxCapacity = maxCapacity;
    this.bookTitle = bookTitle;
    photos = new Photo[maxCapacity];
    captions = new String[maxCapacity];
    nextFreeSlot = 0;
  }

  public int add(Photo photo){
    synchronized(this){
      while(nextFreeSlot == maxCapacity){
          photos.wait();
      }
      photos[nextFreeSlot] = photo;
      nextFreeSlot++;
      notify();
    }
    return nextFreeSlot - 1;
  }

  public void setCaption(int slot, String caption){
    captions[slot] = caption;
  }

  public void upload(SharingSvc svc){
    UploeadThread ut = new UploadThread(svc, bookTitle, photos, captions, this);
    ut.start();
    nextFreeSlot = 0; // We can do this as soon as we start the thread because the run method is synchronized
  }

  private class UploadThread extends Thread{
    SharingSvc svc;
    String bookTitle;
    Photo[] photos;
    String[] captions;
    PhotoBook pb;

    public UploadThread(SharingSvc svc, String bookTitle, Photo[] photos, String[] captions, PhotoBook pb){
      this.svc = svc;
      this.bookTitle = bookTitle;
      this.photos = photos;
      this.captions = captions;
      this.pb = pb;
    }

    public void run(){
      synchronized(pb){
        svc.upload(bookTitle, photos, captions);
        photos = new Photo[pb.maxCapacity];
        captions = new String[pb.maxCapacity];
        pb.notify();
      }
    }

  }
}
