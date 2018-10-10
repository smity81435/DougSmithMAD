//
//  ViewController.swift
//  lab4
//
//  Created by Douglas Smith on 10/9/18.
//  Copyright © 2018 Douglas Smith. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    var user = Bucket()
    let filename = "bucks.plist"
    func dataFileURL(_filename:String) -> URL? {
        let urls = FileManager.default.urls(for:.documentDirectory, in: .userDomainMask)
        var url : URL?
        url = urls.first?.appendingPathComponent(filename)
        return url
    }
    

    @IBOutlet weak var bucketAge: UILabel!
    @IBOutlet weak var bucketLabel: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()
        let fileURL = dataFileURL(_filename: filename)
        if FileManager.default.fileExists(atPath: (fileURL?.path)!){
            let url = fileURL!
            do{
                let data = try Data(contentsOf: url)
                let decoder = PropertyListDecoder()
                user = try decoder.decode(Bucket.self, from: data)
                bucketLabel.text = user.buckItem
                bucketAge.text = String(user.buckAge!)
            }catch{
                print("nofile")
            }
        }
        else{
            print("File does not exist")
        }
        let app = UIApplication.shared
        NotificationCenter.default.addObserver(self, selector: #selector(self.applicationWillResignActive(_:)), name: UIApplication.willResignActiveNotification, object: app)
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    @objc func applicationWillResignActive(_ notification: Notification){
        let fileURL = dataFileURL(_filename: filename)
        let encoder = PropertyListEncoder()
        encoder.outputFormat = .xml
        do{
            let plistData = try encoder.encode(user)
            try plistData.write(to: fileURL!)
        }catch{
            print("write error")
        }
    }
    
    @IBAction func unwindSegue(_ segue:UIStoryboardSegue){
        bucketAge.text = user.buckAge
        bucketLabel.text = user.buckItem
    }


}

