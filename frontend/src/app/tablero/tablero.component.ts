import { Component, OnInit } from '@angular/core';
import { JuegoService } from '../juego.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-tablero',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './tablero.component.html',
  styleUrl: './tablero.component.css'
})
export class TableroComponent implements OnInit {

  tablero: string[] = [];
  juegoId!: number;

  constructor(private juegoService: JuegoService) {}

  ngOnInit(): void {
    this.iniciarJuego();
  }

  iniciarJuego() {
    this.juegoService.crearJuego().subscribe(res => {
      this.juegoId = res.id;
      this.tablero = res.tablero.split('');
    });
  }

  jugar(pos: number) {

  console.log("CLICK en:", pos); // 👈 DEBUG

  if (this.tablero[pos] !== '-') return;

  this.juegoService.jugar(this.juegoId, pos)
    .subscribe((res: any) => {
      console.log("RESPUESTA:", res); // 👈 DEBUG
      this.tablero = res.tablero.split('');
    });
}
}
