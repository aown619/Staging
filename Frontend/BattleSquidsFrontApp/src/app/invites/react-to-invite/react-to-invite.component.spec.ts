import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReactToInviteComponent } from './react-to-invite.component';

describe('ReactToInviteComponent', () => {
  let component: ReactToInviteComponent;
  let fixture: ComponentFixture<ReactToInviteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReactToInviteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReactToInviteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
