package com.netposa.rom.common.kafka.conf;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaCommonConfiguration {

    @Autowired
    private KafkaConsumerProperties consumerProperties;
	@Autowired
	private KafkaProduceProperties produceProperties;

    @Bean
    public ProducerFactory<String, String> producerFactory() {
	Map<String, Object> props = new HashMap<>();

	props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, produceProperties.getBootstrapServers());
	props.put(ProducerConfig.RETRIES_CONFIG, 0);
	props.put(ProducerConfig.BATCH_SIZE_CONFIG, 100);
	props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
	props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 104857600);
	props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
	return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
	return new DefaultKafkaConsumerFactory<>(consumerProperties());
    }

    @Bean
    public Map<String, Object> consumerProperties() {
	Map<String, Object> props = new HashMap<>();
	props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerProperties.getBootstrapServers());
	props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerProperties.getGroupId());
	props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
	props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
	props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 15000);
	props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 5000);
	return props;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
	ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
	factory.setConsumerFactory(consumerFactory());
	return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> jsonKafkaListenerContainerFactory() {
	ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
	factory.setConsumerFactory(consumerFactory());
	factory.setBatchListener(true);
	factory.setMessageConverter(new StringJsonMessageConverter());
	return factory;
    }
}
