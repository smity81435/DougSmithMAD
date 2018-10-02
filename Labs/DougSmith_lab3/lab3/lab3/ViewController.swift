//
//  ViewController.swift
//  lab3
//
//  Created by Douglas Smith on 9/25/18.
//  Copyright © 2018 Douglas Smith. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {

   
    @IBOutlet weak var skiLength: UITextField!
    
    @IBOutlet weak var skiWidth: UITextField!
    
    @IBOutlet weak var numberOfSkis: UITextField!
    
    @IBOutlet weak var time: UILabel!
    
    @IBOutlet weak var wax: UILabel!
    
    @IBAction func onTapGestureRecognized(_ sender: AnyObject) {
        skiLength.resignFirstResponder()
        numberOfSkis.resignFirstResponder()
        skiWidth.resignFirstResponder()
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    
    
    override func viewDidLoad() {
        skiLength.delegate=self
        skiWidth.delegate=self
        numberOfSkis.delegate=self
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    
    
    func updateSkiTotals(){
        var length, width :Float
        var skis:Int?
        
        if skiLength.text!.isEmpty{
            length = 0.0
        } else {
            length = Float(skiLength.text!)!
        }
        if skiWidth.text!.isEmpty{
            width = 0.0
        } else {
            width = Float(skiWidth.text!)!
        }
        if numberOfSkis.text!.isEmpty{
            skis = 0
        } else {
            skis = Int(numberOfSkis.text!)!
        }
        let area = length*width
        
        if skis != nil {
            if skis! > 0 {
                let skiArea = area * Float(skis!)
                let waxWeight = skiArea/10000
                let waxTime = skis!*30
                wax.text = String(waxWeight)
                time.text = String(waxTime)
                
            }else{
                //create an alert controller
                let alert=UIAlertController(title: "Warning", message: "The number of skis must be greater than 0.", preferredStyle: UIAlertControllerStyle.alert)
                let cancelAction=UIAlertAction(title: "Cancel", style: UIAlertActionStyle.cancel, handler: nil)
                alert.addAction(cancelAction)
                let okAction=UIAlertAction(title: "ok", style: UIAlertActionStyle.default, handler: {action in
                    self.numberOfSkis.text="2"
                    self.updateSkiTotals()
                })
                alert.addAction(okAction)
                present(alert, animated:true,completion: nil)
                
            } //end else
        }
    }
    func textFieldDidEndEditing(_ textField: UITextField) {
        updateSkiTotals()
        
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

