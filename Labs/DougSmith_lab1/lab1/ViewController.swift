//
//  ViewController.swift
//  lab1
//
//  Created by Douglas Smith on 9/12/18.
//  Copyright © 2018 Douglas Smith. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var catLabel: UILabel!
    
    @IBOutlet weak var catPic: UIImageView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    @IBAction func sadButton(_ sender: Any) {
        catLabel.text = "SAD KITTY"
        catPic.image = UIImage(named: "sad2")
        
    }
    
    
    @IBAction func madButton(_ sender: Any) {
        catLabel.text = "MAD KITTY"
        catPic.image = UIImage(named: "mad2")
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

