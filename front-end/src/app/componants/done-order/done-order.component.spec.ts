import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoneOrderComponent } from './done-order.component';

describe('DoneOrderComponent', () => {
  let component: DoneOrderComponent;
  let fixture: ComponentFixture<DoneOrderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DoneOrderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DoneOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
