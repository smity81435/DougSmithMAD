//
//  ViewController.swift
//  lab4
//
//  Created by Douglas Smith on 10/9/18.
//  Copyright © 2018 Douglas Smith. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    var user=Bucket()
    

    @IBOutlet weak var bucketAge: UILabel!
    @IBOutlet weak var bucketLabel: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    @IBAction func unwindSegue(_ segue:UIStoryboardSegue){
        bucketAge.text = String(user.buckAge!)
        bucketLabel.text = user.buckItem
    }


}

