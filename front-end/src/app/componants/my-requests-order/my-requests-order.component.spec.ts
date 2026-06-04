import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyRequestsOrderComponent } from './my-requests-order.component';

describe('MyRequestsOrderComponent', () => {
  let component: MyRequestsOrderComponent;
  let fixture: ComponentFixture<MyRequestsOrderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyRequestsOrderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyRequestsOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
