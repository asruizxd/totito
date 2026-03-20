import { Component } from '@angular/core';
import { TableroComponent } from './tablero/tablero.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [TableroComponent],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App {}
