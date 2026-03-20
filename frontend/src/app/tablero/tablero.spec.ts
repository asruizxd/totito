import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableroComponent } from './tablero';

describe('TableroComponent', () => {
  let component: TableroComponent;
  let fixture: ComponentFixture<TableroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TableroComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(TableroComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
