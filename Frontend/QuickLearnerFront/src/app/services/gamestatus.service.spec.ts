import { TestBed } from '@angular/core/testing';

import { GamestatusService } from './gamestatus.service';

describe('GamestatusService', () => {
  let service: GamestatusService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GamestatusService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
