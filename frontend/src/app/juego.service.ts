import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class JuegoService {

  private api = 'http://localhost:8080/api/juego';

  constructor(private http: HttpClient) {}

  crearJuego() {
    return this.http.post<any>(this.api, {});
  }

  jugar(id: number, posicion: number) {
    return this.http.post<any>(`${this.api}/${id}/movimiento`, {
      posicion: posicion
    });
  }
}
