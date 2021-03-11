import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-material-dialog',
  templateUrl: './material-dialog.component.html',
  styleUrls: ['./material-dialog.component.scss']
})
export class MaterialDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<MaterialDialogComponent>) { }

  fileName = 'Upload File';

  ngOnInit(): void {
  }

  closeMaterial(): void {
    // console.log('close material');
    this.dialogRef.close();
  }

  onFileChanged(event: any): any {
    this.data.material = event.target.files[0];
    if(this.data.material) {
      this.fileName = this.data.material.name;
    }
    else {
      this.fileName = 'Upload File';
    }
  }

}
