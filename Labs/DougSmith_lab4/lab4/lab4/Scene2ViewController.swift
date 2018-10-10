//
//  Scene2ViewController.swift
//  lab4
//
//  Created by Douglas Smith on 10/9/18.
//  Copyright © 2018 Douglas Smith. All rights reserved.
//

import UIKit

class Scene2ViewController: UIViewController, UITextFieldDelegate {

    @IBOutlet weak var userAge: UITextField!
    @IBOutlet weak var userItem: UITextField!
    
    override func viewDidLoad() {
        
        userAge.delegate = self
        userItem.delegate = self
        super.viewDidLoad()
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        self.view.endEditing(true)
        return false
    }
    
    

    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "doneBucket"{
            let scene1ViewController = segue.destination as! ViewController
            if userItem.text!.isEmpty == false{
                scene1ViewController.user.buckItem=userItem.text
                
            }
            if userAge.text!.isEmpty == false{
                scene1ViewController.user.buckAge = userAge.text
            }
        }
//         segue.destination.
        // Pass the selected object to the new view controller.
    }
 

}
