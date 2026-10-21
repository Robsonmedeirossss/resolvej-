SET search_path TO sc_resolveja;

INSERT INTO services (id, name, description) VALUES
(nextval('sc_resolveja.seq_services'), 'Pintura de casa', 'Serviço completo de pintura interna e externa para casas e apartamentos.'),
(nextval('sc_resolveja.seq_services'), 'Instalação elétrica', 'Instalação, manutenção e reparo de tomadas, interruptores, luminárias e circuitos elétricos.'),
(nextval('sc_resolveja.seq_services'), 'Encanamento', 'Manutenção e reparo de vazamentos, torneiras, tubulações, pias e sistemas hidráulicos.'),
(nextval('sc_resolveja.seq_services'), 'Limpeza residencial', 'Limpeza completa de casas e apartamentos, incluindo pisos, banheiros, cozinha e áreas comuns.'),
(nextval('sc_resolveja.seq_services'), 'Montagem de móveis', 'Montagem profissional de móveis residenciais e comerciais de diferentes modelos e fabricantes.'),
(nextval('sc_resolveja.seq_services'), 'Manutenção de ar-condicionado', 'Limpeza, manutenção preventiva e verificação do funcionamento de aparelhos de ar-condicionado.'),
(nextval('sc_resolveja.seq_services'), 'Instalação de ventilador de teto', 'Instalação e configuração de ventiladores de teto, incluindo ligação elétrica e fixação.'),
(nextval('sc_resolveja.seq_services'), 'Jardinagem', 'Manutenção de jardins, poda de plantas, corte de grama e cuidados gerais com áreas verdes.'),
(nextval('sc_resolveja.seq_services'), 'Fotografia para eventos', 'Serviço de fotografia para aniversários, confraternizações, casamentos e outros eventos.'),
(nextval('sc_resolveja.seq_services'), 'Aulas particulares', 'Aulas particulares personalizadas para estudantes do ensino fundamental e médio.'),
(nextval('sc_resolveja.seq_services'), 'Manutenção de computadores', 'Formatação, instalação de programas, configuração do sistema e diagnóstico de problemas em computadores.'),
(nextval('sc_resolveja.seq_services'), 'Instalação de câmeras de segurança', 'Instalação e configuração de câmeras de segurança para residências e pequenos estabelecimentos.');