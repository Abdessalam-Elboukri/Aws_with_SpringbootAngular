import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {FileHandler} from "../../models/fileHandler";
import {FileService} from "../../services/file.service";
import {environment} from "../../../environments/environment";
import {FileData} from "../../models/fileData.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css']
})
export class UploadFileComponent implements OnInit {

  file!:FileData

  constructor( private fileService:FileService, private router:Router ) {
    this.file=new FileData()
  }

  ngOnInit(): void {

  }

  onFileChanged(event:any) {
    if(event.target.files){
      const file = event.target.files[0];
      const fileHandler : FileHandler ={
        file : file
      }
      this.file.fileData=fileHandler;
    }
  }

  prepareFormData(file:FileData): FormData{
    const formData = new FormData();

    formData.append('fileInfo',
      new Blob([JSON.stringify(file)],{type:'application/json'}));

    formData.append('MyFile',
      file.fileData.file,
      file.fileData.file.name)

    return formData;
  }

  addFile(form:NgForm){
    if(form.valid){
      console.log("starting upload file ...")
      const fileFormData=this.prepareFormData(this.file);
      this.fileService.uploadFile(fileFormData).subscribe((res)=>{
        this.router.navigate([""])
      })
    }
  }



}
