//
//  ViewController.swift
//  waxhead
//
//  Created by Douglas Smith on 10/2/18.
//  Copyright © 2018 Douglas Smith. All rights reserved.
//

import UIKit
import CoreLocation



class ViewController: UIViewController {
    var latit = Double()
    var longi = Double()
    var locname = String()
    let locationManager = CLLocationManager()
    @IBAction func button(_ sender: Any) {
            locationManager.requestLocation()
//            getWeather()
    }
    
    @IBOutlet weak var waxpic: UIImageView!
    @IBOutlet weak var temp: UILabel!
    @IBOutlet weak var city: UILabel!
    @IBOutlet weak var coord: UILabel!
    @IBOutlet weak var waxtype: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        locationManager.delegate = self
        locationManager.requestWhenInUseAuthorization()
//        getWeather()
        }
    func lookUpCurrentLocation(completionHandler: @escaping (CLPlacemark?) -> Void ) {
        // Use the last reported location.
        if let lastLocation = self.locationManager.location {
            let geocoder = CLGeocoder()
            
            // Look up the location and pass it to the completion handler
            geocoder.reverseGeocodeLocation(lastLocation, completionHandler: { (placemarks, error) in
                if error == nil {
                    let firstLocation = placemarks?[0]
                    completionHandler(firstLocation)
                }
                else {
                    // An error occurred during geocoding.
                    completionHandler(nil)
                }
            })
        }
        else {
            // No location was available.
            completionHandler(nil)
        }
    }



}

extension ViewController: CLLocationManagerDelegate {

    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        if let lat = locations.last?.coordinate.latitude, let long = locations.last?.coordinate.longitude {
            print("\(lat),\(long)")
            coord.text = String("\(lat) , \(long)")
            latit = lat
            longi = long
            lookUpCurrentLocation { geoLoc in
                self.city.text = (geoLoc?.locality)!
                self.locname = (geoLoc?.locality)!
                self.getWeather()
                print(geoLoc?.locality ?? "unknown Geo location")
            }
        } else {
            print("No coordinates")
        }
    }
    func locationManager(_ manager: CLLocationManager, didFailWithError error: Error) {
        print(error)
    }
    func getWeather() {
//        let positron = self.locname
        let session = URLSession.shared
        print("https://api.darksky.net/forecast/1ca62cebe1ea276e36922031c58a54e5/\(latit),\(longi)")
        let weatherURL = URL(string: "https://api.darksky.net/forecast/1ca62cebe1ea276e36922031c58a54e5/\(latit),\(longi)")!
        let dataTask = session.dataTask(with: weatherURL) {
            (data: Data?, response: URLResponse?, error: Error?) in
            if let error = error {
                print("Error:\n\(error)")
            } else {
                if let data = data {
                    let dataString = String(data: data, encoding: String.Encoding.utf8)
                    print("All the weather data:\n\(dataString!)")
                    if let jsonObj = try? JSONSerialization.jsonObject(with: data, options: .allowFragments) as? NSDictionary {
                        if let mainDictionary = jsonObj!.value(forKey: "currently") as? NSDictionary {
                            if let temperature = mainDictionary.value(forKey: "temperature") {
                                
                                DispatchQueue.main.async {
                                    self.temp.text = "\(temperature)°F"
                                    let numtemp = temperature as! Double
                                    if(numtemp < 14){
                                        self.waxpic.image = UIImage(named: "purp.png")
                                        self.waxtype.text = "Violet Wax"
                                    }else if(numtemp >= 14 && numtemp < 20){
                                        self.waxpic.image = UIImage(named: "blue.png")
                                        self.waxtype.text = "Blue Wax"
                                    }else if(numtemp >= 20 && numtemp < 32){
                                        self.waxpic.image = UIImage(named: "red.png")
                                        self.waxtype.text = "Red Wax"
                                    }else if(numtemp >= 32 ){
                                        self.waxpic.image = UIImage(named: "yell.png")
                                        self.waxtype.text = "Yellow Wax"
                                    }
                                }
                            }
                        } else {
                            print("Error: unable to find temperature in dictionary")
                        }
                    } else {
                        print("Error: unable to convert json data")
                    }
                } else {
                    print("Error: did not receive data")
                }
            }
        }
        dataTask.resume()
    }
}
