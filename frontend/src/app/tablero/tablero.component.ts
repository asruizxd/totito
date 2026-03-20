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

  estado: string = 'EN_CURSO';
  tablero: string[] = ["-", "-", "-", "-", "-", "-", "-", "-", "-"];
  juegoId!: number;
  ganador: string | null = null;

  constructor(private juegoService: JuegoService) { }

  ngOnInit(): void {
    this.iniciarJuego();
  }

  iniciarJuego() {
    this.juegoService.crearJuego().subscribe(res => {
      this.juegoId = res.id;

      setTimeout(() => {
        if (res.tablero) {
          this.tablero = res.tablero.split('');
        }
      });
    });
  }

  jugar(pos: number) {

    if (this.estado === 'FINALIZADO') {
      console.log("El juego ya terminó");
      return;
    }

    if (this.tablero[pos] !== '-') return;

    this.juegoService.jugar(this.juegoId, pos)
      .subscribe({
        next: (res: any) => {
          this.tablero = res.tablero.split('');
          this.estado = res.estado;
        },
        error: (err) => {
          console.error("Error del backend:", err);

          // Opcional: marcar como finalizado
          this.estado = 'FINALIZADO';
        }
      });
  }
}
